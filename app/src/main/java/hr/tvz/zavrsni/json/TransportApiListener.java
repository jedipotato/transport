package hr.tvz.zavrsni.json;

import hr.tvz.zavrsni.domain.api.BasicModel;

/**
 * Sučelje odgovorno za slušanje na odaziv API odgovora
 */
public interface TransportApiListener<T extends BasicModel> {

    void onApiResponse(T response);
    void onApiFailure();

}
