package trackapp.icube04backend.infrastructure.db.jpa.projections;

public interface ClientAgeAggregatesProjection {
    Long getTotal();        // COUNT(*)
    Double getAverageAge(); // AVG(age)
    Double getStdDevAge();  // STDDEV_POP(age)
}
