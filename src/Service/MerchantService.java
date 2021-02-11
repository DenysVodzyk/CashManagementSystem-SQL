package Service;

import Repository.MerchantRepository;
import Entity.Merchant;

import java.time.LocalDate;
import java.util.List;

public class MerchantService {
    private MerchantRepository merchantRepository = new MerchantRepository();

    public List<Merchant> getAll() {
        return merchantRepository.getAll();
    }

    public Merchant getById(int id) {
        Merchant merchantById = null;
        for (Merchant merchant : getAll()) {
            if (merchant.getId() == id) {
                merchantById = merchant;
            }
        }
        return merchantById;
    }

    //Lesson 7, clause 5
    public void sendFunds() {
        for (Merchant merchant : getAll()) {
            if (merchant.getNeedToSend() >= merchant.getMinSum()) {
                merchant.setLastSent(LocalDate.now());
                double total = merchant.getSent() + merchant.getNeedToSend();
                merchant.setSent(total);
                merchant.setNeedToSend(0);
                merchantRepository.updateSendFunds(merchant);
            }
        }
    }


}
