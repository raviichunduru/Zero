package config;

public enum TestEnv {
  LOCALHOST("localhost"),
  DEVELOP("develop"),
  STAGING("staging");

  private String value;

  TestEnv(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
