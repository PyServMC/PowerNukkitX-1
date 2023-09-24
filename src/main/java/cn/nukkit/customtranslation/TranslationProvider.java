package cn.nukkit.customtranslation;

public abstract class TranslationProvider {
    public abstract String translate(String lang, String key);
    public abstract String translate(String lang, String key, String... params);
}
