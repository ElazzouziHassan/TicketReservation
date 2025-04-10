package com.ticketservice.service;

import com.ticketservice.model.Billet;
import com.ticketservice.repository.BilletRepository;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "BilletService")
public class BilletService {
    
    private BilletRepository billetRepository = new BilletRepository();
    
    @WebMethod
    public List<Billet> getBillets() {
        return billetRepository.findAll();
    }
    
    @WebMethod
    public Billet getBilletById(@WebParam(name = "billetId") Long billetId) {
        return billetRepository.findById(billetId);
    }
    
    @WebMethod
    public List<Billet> getBilletsByEvenementId(@WebParam(name = "evenementId") Long evenementId) {
        return billetRepository.findByEvenementId(evenementId);
    }
    
    @WebMethod
    public boolean updateQuantiteDisponible(
            @WebParam(name = "billetId") Long billetId, 
            @WebParam(name = "nouvelleQuantite") Integer nouvelleQuantite) {
        try {
            billetRepository.updateQuantiteDisponible(billetId, nouvelleQuantite);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
