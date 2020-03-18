package com.pinyougou.service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.SellerService;
import com.pinyougou.pojo.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class UserDetailsServiceImpl implements UserDetailsService {

    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //授权信息
        List<GrantedAuthority> granteds = new ArrayList<GrantedAuthority>();
        granteds.add(new SimpleGrantedAuthority("ROLE_SELLER"));

        //在数据库查询数据
        Seller seller = sellerService.findOne(name);
        if (seller == null) {
            return null;
        }
        if (!("1".equals(seller.getStatus()))) {
            return null;
        }
        return new User(name,seller.getPassword(),granteds);
    }
}