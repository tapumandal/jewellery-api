package me.tapumandal.jewellery.domain.ref_code;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefCodeRewardServiceImpl implements RefCodeRewardService {

    @Autowired
    RefCodeRewardRepository refCodeRewardRepository;

    private RefCodeReward refCodeReward;

    public RefCodeRewardServiceImpl(){}

    public RefCodeRewardServiceImpl(RefCodeReward refCodeReward){
        this.refCodeReward = refCodeReward;
    }

    @Override
    public RefCodeReward create(RefCodeRewardDto refCodeRewardDto) {

        RefCodeReward pro = new RefCodeReward(refCodeRewardDto);
        Optional<RefCodeReward> refCodeReward;

//        try{
            int refCodeRewardId = refCodeRewardRepository.create(pro);
            refCodeReward = Optional.ofNullable(refCodeRewardRepository.getRefCodeRewardFirstTime(refCodeRewardId));
//        }catch (Exception e){
//            return null;
//        }

        if(refCodeReward.isPresent()){
            return refCodeReward.get();
        }else{
            return null;
        }
    }

    @Override
    public RefCodeReward update(RefCodeRewardDto refCodeRewardDto) {


        RefCodeReward pro = new RefCodeReward(refCodeRewardDto);

        Optional<RefCodeReward> refCodeReward;
        try{
            int proId = refCodeRewardRepository.update(pro);
            refCodeReward = Optional.ofNullable(refCodeRewardRepository.getById(proId));
        }catch (Exception e){
            return null;
        }

        if(refCodeReward.isPresent()){
            return refCodeReward.get();
        }else{
            return null;
        }

    }

    @Override
    public List<RefCodeReward> getAll(Pageable pageable, ListFilter listFilter) {
        Optional<List<RefCodeReward>> refCodeRewards = Optional.ofNullable(refCodeRewardRepository.getAll(pageable, listFilter));

        if(refCodeRewards.isPresent()){
            return refCodeRewards.get();
        }else{
            return null;
        }
    }

    @Override
    public RefCodeReward getById(int id) {

        Optional<RefCodeReward> refCodeReward = Optional.ofNullable(refCodeRewardRepository.getById(id));

        if(refCodeReward.isPresent()){
            return refCodeReward.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return refCodeRewardRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public RefCodeReward getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<RefCodeReward> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<RefCodeReward> refCodeReward = Optional.ofNullable(refCodeRewardRepository.getById(id));
        if(refCodeReward.isPresent()){
            if(refCodeReward.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return refCodeReward.isDeleted();
    }

    @Override
    public MyPagenation getPageable(Pageable pageable) {
        return null;
    }

    @Override
    public int getAllEntityCount(Pageable pageable, ListFilter listFilter) {
        return 0;
    }

}
