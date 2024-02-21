package com.ahmed.invoicemanagment.repository.implementation;

import com.ahmed.invoicemanagment.domain.Role;
import com.ahmed.invoicemanagment.domain.User;
import com.ahmed.invoicemanagment.exception.ApiException;
import com.ahmed.invoicemanagment.repository.RoleRepository;
import com.ahmed.invoicemanagment.repository.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static com.ahmed.invoicemanagment.enumeration.RoleType.ROLE_USER;
import static com.ahmed.invoicemanagment.query.UserQuery.*;
import static java.util.Objects.*;



public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbc, RoleRepository<Role> roleRepository, BCryptPasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    @Override
    public User create(User user) {
        // check email is unique
        if(getEmailCount(user.getEmail().trim().toLowerCase()) > 0) throw new ApiException("Email is already present try different email");
        // save the user
        try{
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
//            Add role to the user
            roleRepository.addRoleToUser(user.getId(),ROLE_USER.name());
        }catch(EmptyResultDataAccessException exception)
        {}
        catch (Exception exception) {}



        return null;
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }



    private SqlParameterSource getSqlParameterSource(User user) {

        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName",user.getLastName())
                .addValue("email",user.getEmail())
                .addValue("password",encoder.encode(user.getPassword()));

    }

    private int getEmailCount(String email) {
        jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email",email),Integer.class );

        return 0;
    }

}
