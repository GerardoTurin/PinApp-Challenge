package trackapp.icube04backend.infrastructure.adapters.monitoring_module.restcontroller.dtos;

public enum HoursOption {

    SIX_HOURS(6),
    TWELVE_HOURS(12),
    THIRTY_HOURS(30);

    private final int horas;

    private HoursOption(int horas) {
        this.horas = horas;
    }

    public int getHoras() {
        return this.horas;
    }

    public static HoursOption fromHoras(int horas) {
        for (HoursOption option : values()) {
            if (option.horas == horas) {
                return option;
            }
        }
        throw new IllegalArgumentException("Valor de tiempo no valido: " + horas);
    }
}
