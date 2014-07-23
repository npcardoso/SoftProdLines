package splat;

import splat.backtracker.Backtracker;

public class GCCVariables extends MultiVariables {

  private static GCCVariables SINGLETON;

  public static GCCVariables getSINGLETON() {
    if (SINGLETON == null) {
      SINGLETON = new GCCVariables();
    }
    return SINGLETON;
  }

  public GCCVariables() {
    restore();
  }

  //TODO check negative values
  public enum VARS {
    use_pipes                   (new String[] {"","-pipe"}, new String[]{"0", "1"}),
    warn_format                 (new String[] {"-Wno-format","-Wformat","-Wformat=2"}, new String[]{"0", "1", "2"}),
    warn_format_contains_nul    (new String[] {"-Wno-format-contains-nul",""}, new String[]{"0", "1"}), 
    warn_format_extra_args      (new String[] {"-Wno-format-extra-args",""}, new String[]{"0", "1"}),
    warn_format_zero_length     (new String[] {"-Wno-format-zero-length",""}, new String[]{"0", "1"}),
    warn_format_nonliteral      (new String[] {"-Wno-format-nonliteral","-Wformat-nonliteral"}, new String[]{"0", "1"}),
    warn_format_security        (new String[] {"-Wno-format-security","-Wformat-security"}, new String[]{"0", "1"}), 
    warn_format_y2k             (new String[] {"-Wno-format-y2k","-Wformat-y2k"}, new String[]{"0", "1"}),
    warn_conversion             (new String[] {"-Wno-conversion","-Wconversion"}, new String[]{"0", "1"}),
    flag_asan                   (new String[] {"","-fsanitize=address"}, new String[]{"0", "1"}),
    warn_implicit               (new String[] {"","-Wimplicit"}, new String[]{"0", "1"}),
    warn_implicit_function_declaration (new String[] {"","-Wimplicit-function-declaration"}, new String[]{"-1", "1"}), 
    warn_implicit_int           (new String[] {"","-Wimplicit-int"}, new String[]{"0", "1"}),
    warn_inh_var_ctor           (new String[] {"","-Winherited-variadic-ctor"}, new String[]{"0", "1"}),
    warn_init_self              (new String[] {"","-Winit-self"}, new String[]{"0", "1"}),
    warn_inline                 (new String[] {"","-Winline"}, new String[]{"0", "1"}),
    warn_suggest_attribute_const(new String[] {"-Wmissing-format-attribute","-Wsuggest-attribute=const"}, new String[]{"0", "1"}),
    warn_suggest_attribute_format(new String[] {"-Wmissing-format-attribute","-Wsuggest-attribute=format"}, new String[]{"0", "1"}),
    warn_suggest_attribute_noreturn(new String[] {"-Wmissing-format-attribute","-Wsuggest-attribute=noreturn"}, new String[]{"0", "1"}),
    warn_suggest_attribute_pure (new String[] {"-Wmissing-format-attribute","-Wsuggest-attribute=pure"}, new String[]{"0", "1"}),
    pedantic                     (new String[] {"-Wno-pedantic","-pedantic"}, new String[]{"0", "1"})
    
    ;
    
    public final String[] options; // name of the option
    public String[] values; // value of the option
    
    VARS(String[] options, String[] values) {
      this.options = options;
      this.values = values;
    }
    
    int nOptions() {
      return options.length;
    };

    public String getOption(int index) {
      return options[index];
    };

    public String getOptionValue(int index) {
      return values[index];
    };
  };
  
  /**
   * Add validation rules here.
   * @return
   */
  public boolean validate() {
    if (map.get(VARS.warn_format).equals(VARS.warn_format.options[0]) &&
          (map.get(VARS.warn_format_contains_nul).equals(VARS.warn_format_contains_nul.options[0])
          || map.get(VARS.warn_format_extra_args).equals(VARS.warn_format_extra_args.options[0])
          || map.get(VARS.warn_format_zero_length).equals(VARS.warn_format_zero_length.options[0]))) {
      return false;
    }
    return true;
  }

  //TODO change the value of the map to an integer 
  private void init() {
    map.put(VARS.use_pipes, "?");
    map.put(VARS.warn_format, "?");
    map.put(VARS.warn_format_contains_nul, "?");
    map.put(VARS.warn_format_extra_args, "?");
    map.put(VARS.warn_format_nonliteral, "?");
    map.put(VARS.warn_format_security, "?");
    map.put(VARS.warn_format_y2k, "?");
    map.put(VARS.warn_format_zero_length, "?");
    map.put(VARS.warn_conversion, "?");
    map.put(VARS.flag_asan, "?");
    map.put(VARS.warn_implicit, "?");
    map.put(VARS.warn_implicit_function_declaration, "?");
    map.put(VARS.warn_implicit_int, "?");
    map.put(VARS.warn_inh_var_ctor, "?");
    map.put(VARS.warn_init_self, "?");
    map.put(VARS.warn_inline, "?");
    map.put(VARS.warn_suggest_attribute_const, "?");
    map.put(VARS.warn_suggest_attribute_format, "?");
    map.put(VARS.warn_suggest_attribute_noreturn, "?");
    map.put(VARS.warn_suggest_attribute_pure, "?");
    map.put(VARS.pedantic, "?");
  }
  
  public String getPeek(VARS f, Backtracker bt, int offset) {
    String tmp_var_name = "";
    String tmp = map.get(f);
    if (tmp == "?") {
      int nOpts = f.nOptions(); 
      int next = bt.chooseAndPeek(0, nOpts-1, offset, f.values);
      tmp_var_name = f.getOption(next);
      tmp = f.getOptionValue(next);
      map.put(f, tmp);
    }
    return tmp_var_name;
  }

  public String get(VARS f) {
    return map.get(f); 
  }

  @Override
  public void restore() {
    map.clear();
    init();
  }

  
}