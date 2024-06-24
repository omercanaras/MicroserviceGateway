package com.omercan.gateway_app.model.service;

import com.omercan.gateway_app.model.entity.User;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.omercan.gateway_app.utility.Util;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractUserService
{
    @Override
    public Optional<User> findByUsername(String username)
    {
        try
        {
            return userRepository.findByUsername(username);
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public User save(User entity)
    {
        try
        {
            entity.setCreated(new Date());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            return userRepository.save(entity);
        }
        catch(IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch(OptimisticLockingFailureException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public User findById(Integer id)
    {
        try{
            return userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Entity is not found") );

        }
        catch (IllegalArgumentException e )
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (NullPointerException e )
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (RuntimeException e )
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Integer id)
    {
        try
        {
            userRepository.deleteById(id);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
        }
    }
}
