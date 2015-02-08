package hr.tvz.zavrsni.json;

/**
 * Sučelje odgovorno za slušanje na odaziv API odgovora
 */
public interface TransportApiListener<T> {

    void onApiResponse(T response);
    void onApiFailure();

}
