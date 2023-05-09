package mc.obliviate.arenacore.util.invite;

import mc.obliviate.arenacore.match.task.MatchTask;
import mc.obliviate.arenacore.util.Preconditions;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class Invite {

    private final UUID sender;
    private final UUID receiver;
    private final long expireOutTime;
    private final Consumer<Invite> response;
    private InviteState state = InviteState.PENDING;
    private final MatchTask expireMatchTask = new MatchTask();

    protected Invite(UUID sender, UUID receiverUniqueId, long expireOutTime, Consumer<Invite> response) {
        this.sender = sender;
        this.receiver = receiverUniqueId;
        this.expireOutTime = expireOutTime;
        this.response = response;

        InviteRecipient.getInviteRecipient(receiverUniqueId).addInvite(this);

        expireMatchTask.after((expireOutTime - System.currentTimeMillis()) / 50).run(() -> {
            if (state.equals(InviteState.PENDING)) {
                response(InviteState.EXPIRED);
            }
        });
    }

    /**
     * Purpose of this class is,
     * building new invite class.
     */
    public static class Builder {

        private UUID sender;
        private UUID receiver;
        private long expireTime;
        private Consumer<Invite> response;

        protected Builder() {
        }

        /**
         * Builds an invitation from the builder and sends it to the receiver.
         * @return built invite
         */
        public Invite build() {
            if (checkInvitedBefore()) throw new IllegalStateException(sender + " already invited.");
            return new Invite(sender, receiver, expireTime, response);
        }

        public boolean checkInvitedBefore() {
            InviteRecipient inviteRecipient = InviteRecipient.getInviteRecipient(receiver);
            for (Invite invite : new ArrayList<>(inviteRecipient.getInvites())) {
                if (invite.sender.equals(sender)) {
                    if (invite.expireOutTime < System.currentTimeMillis()) {
                        inviteRecipient.removeInvite(invite);
                    }
                    return true;
                }
            }
            return false;
        }

        public Consumer<Invite> getResponse() {
            return response;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public UUID getReceiver() {
            return receiver;
        }

        public UUID getSender() {
            return sender;
        }

        public Builder onResponse(Consumer<Invite> action) {
            Preconditions.checkArgument(action != null, "action cannot be null");
            this.response = action;
            return this;
        }

        public Builder setExpireTimeLater(long msLater) {
            Preconditions.checkArgument(msLater > 0, "expire time cannot be negative");
            return setExpireTime(System.currentTimeMillis() + msLater);
        }

        public Builder setExpireTime(long expireTime) {
            Preconditions.checkArgument(expireTime > System.currentTimeMillis(), "expire time cannot be smaller than now");
            this.expireTime = expireTime;
            return this;
        }

        public Builder setReceiver(UUID receiver) {
            Preconditions.checkArgument(receiver != null, "receiver cannot be null");
            this.receiver = receiver;
            return this;
        }

        public Builder setSender(UUID sender) {
            Preconditions.checkArgument(sender != null, "sender cannot be null");
            this.sender = sender;
            return this;
        }
    }

    public static Builder create() {
        return new Builder();
    }

    public void response(InviteState inviteState) {
        state = inviteState;
        expireMatchTask.cancel();
        InviteRecipient.getInviteRecipient(receiver).removeInvite(this);
        response.accept(this);
    }

    public InviteState getState() {
        return state;
    }

    public long getExpireOutTime() {
        return expireOutTime;
    }

    public UUID getRecipientUniqueId() {
        return receiver;
    }

    public UUID getSenderUniqueId() {
        return sender;
    }

    /**
     * Purpose of this class is,
     * defining current state of invite.
     */
    public enum InviteState {
        NOT_SENT,
        PENDING,
        ACCEPTED,
        REJECTED,
        EXPIRED,
        CANCELLED
    }

}
