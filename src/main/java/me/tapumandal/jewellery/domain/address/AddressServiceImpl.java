package me.tapumandal.jewellery.domain.address;

import me.tapumandal.jewellery.entity.ListFilter;
import me.tapumandal.jewellery.util.MyPagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    private Address address;

    public AddressServiceImpl(){}

    public AddressServiceImpl(Address address){
        this.address = address;
    }

    @Override
    public Address create(AddressDto addressDto) {

        Address pro = new Address(addressDto);
        Optional<Address> address;

//        try{
            int addressId = addressRepository.create(pro);
            address = Optional.ofNullable(addressRepository.getById(addressId));
//        }catch (Exception e){
//            return null;
//        }

        if(address.isPresent()){
            return address.get();
        }else{
            return null;
        }
    }

    @Override
    public Address update(AddressDto addressDto) {


        Address pro = new Address(addressDto);

        Optional<Address> address;
//        try{
            int proId = addressRepository.update(pro);
            address = Optional.ofNullable(addressRepository.getById(proId));
//        }catch (Exception e){
//            return null;
//        }

        if(address.isPresent()){
            return address.get();
        }else{
            return null;
        }

    }

    @Override
    public List<Address> getAll(Pageable pageable, ListFilter listFilter) {
        Optional<List<Address>> addresss = Optional.ofNullable(addressRepository.getAll(pageable, listFilter));

        if(addresss.isPresent()){
            return addresss.get();
        }else{
            return null;
        }
    }

    @Override
    public Address getById(int id) {

        Optional<Address> address = Optional.ofNullable(addressRepository.getById(id));

        if(address.isPresent()){
            return address.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return addressRepository.delete(id);
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public Address getByValue(String kye, String value) {
        return null;
    }

    @Override
    public List<Address> getAllByValue(String kye, String value) {
        return null;
    }

    @Override
    public boolean isActive(int id) {
        Optional<Address> address = Optional.ofNullable(addressRepository.getById(id));
        if(address.isPresent()){
            if(address.get().isActive()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isDeleted(int id) {
        return address.isDeleted();
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
