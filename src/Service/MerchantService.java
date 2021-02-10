package Service;

import DBUtils.MerchantRepository;
import Entity.Merchant;

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

}
