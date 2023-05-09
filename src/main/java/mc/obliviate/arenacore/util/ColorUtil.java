package mc.obliviate.arenacore.util;

public class ColorUtil {

    public static final char COLOR_CHAR = '§';
    public static final char ALT_COLOR_CHAR = '&';

    private static ColorUtil instance;

    public static ColorUtil getInstance() {
        if (instance == null) return instance = new ColorUtil();
        return instance;
    }

    public static void setInstance(ColorUtil instance) {
        ColorUtil.instance = instance;
    }

    public String color(String message) {
        if (message == null) return null;
        char[] b = message.toCharArray();

        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == ALT_COLOR_CHAR && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    public String lastColorChar(String input) {
        String result = "";
        int length = input.length();
        for (int index = length - 1; index > -1; index--) {
            char section = input.charAt(index);
            if (section == ALT_COLOR_CHAR && index < length - 1) {
                char colorChar = input.charAt(index + 1);
                result = String.valueOf(ALT_COLOR_CHAR) + colorChar;
            }
        }
        return result;
    }

}
