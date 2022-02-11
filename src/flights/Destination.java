package flights;

public enum Destination {
  SOUTH_ISLAND("South Island"),
  PARIS("Paris"),
  OSAKA("Osaka"),
  MAUI("Maui"),
  ROME("Rome"),
  NEW_YORK("New York"),
  LONDON("London"),
  MADRID("Madrid"),
  PHUKET("Phuket"),
  TOKYO("Tokyo"),
  MALDIVES("Maldives"),
  BALI("Bali"),
  SYDNEY("Sydney"),
  DUBAI("Dubai"),
  FLORENCE("Florence"),
  PRAGUE("Prague"),
  SINGAPORE("Singapore"),
  BERLIN("Berlin"),
  LAS_VEGAS("Las Vegas"),
  VIENNA("Vienna"),
  TAHITI("Tahiti"),
  BEIJING("Beijing"),
  MIAMI("Miami"),
  ZURICH("Zurich"),
  SAO_PAULO("Sao Paulo"),
  BORA_BORA("Bora Bora"),
  TEL_AVIV("Tel Aviv");


  private final String name;

  Destination(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
