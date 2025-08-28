package CRBT.Billing_Service.Service;

import CRBT.Billing_Service.Model.Subscription;
import CRBT.Billing_Service.Repository.Subscription_Repository;
import CRBT.Billing_Service.BillingDTO.UserDTO;
import CRBT.Billing_Service.Client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Subscription_Service {

    @Autowired
    private Subscription_Repository subrepo;

    @Autowired
    private UserClient userClient;

    public List<Subscription> getByUserId(Long user_id) {
        return subrepo.findByUsers_id(user_id);
    }

    public List<Subscription> getAll() {
        return subrepo.findAll();
    }

    public List<Subscription> getByUserName(String username) {
        UserDTO user = userClient.getUserByUsername(username);
        if (user == null || user.getUsers_id() == null) {
            throw new RuntimeException("User not found for username: " + username);
        }
        return subrepo.findByUsers_id(user.getUsers_id());
    }

    public Subscription createSub(Subscription sub) {
        sub.setCreatedAt(LocalDateTime.now());
        sub.setUpdatedAt(LocalDateTime.now());
        if (sub.getIsActive() == null) sub.setIsActive(true);
        if (sub.getStatus() == null) sub.setStatus(Subscription.SubStatus.ACTIVE);
        return subrepo.save(sub);
    }

    public Subscription updateSub(Long users_id, Subscription updatedSub) {
        List<Subscription> userSubs = subrepo.findByUsers_id(users_id);
        if (userSubs.isEmpty()) {
            throw new RuntimeException("No subscription found for user with id " + users_id);
        }

        Subscription existing = userSubs.get(0);

        existing.setPlanName(updatedSub.getPlanName());
        existing.setPrice(updatedSub.getPrice());
        existing.setCurrency(updatedSub.getCurrency());
        existing.setStartDate(updatedSub.getStartDate());
        existing.setEndDate(updatedSub.getEndDate());
        existing.setIsActive(updatedSub.getIsActive());
        existing.setAutoRenew(updatedSub.getAutoRenew());
        existing.setSong_id(updatedSub.getSong_id());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setStatus(updatedSub.getStatus());

        return subrepo.save(existing);
    }

    public Subscription adminUpdateSub(Long subscriptionId, Subscription updatedSub) {
        Optional<Subscription> opt = subrepo.findById(subscriptionId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Subscription not found with id " + subscriptionId);
        }

        Subscription existing = opt.get();

        existing.setUsers_id(updatedSub.getUsers_id());
        existing.setPlanName(updatedSub.getPlanName());
        existing.setPrice(updatedSub.getPrice());
        existing.setCurrency(updatedSub.getCurrency());
        existing.setStartDate(updatedSub.getStartDate());
        existing.setEndDate(updatedSub.getEndDate());
        existing.setIsActive(updatedSub.getIsActive());
        existing.setAutoRenew(updatedSub.getAutoRenew());
        existing.setSong_id(updatedSub.getSong_id());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setStatus(updatedSub.getStatus());

        return subrepo.save(existing);
    }
}
