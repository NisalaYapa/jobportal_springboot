package com.nisala.jobportal.services;

import com.nisala.jobportal.entity.JobSeekerProfile;
import com.nisala.jobportal.entity.RecruiterProfile;
import com.nisala.jobportal.entity.Users;
import com.nisala.jobportal.repository.JobSeekerProfileRepository;
import com.nisala.jobportal.repository.RecruiterProfileRepository;
import com.nisala.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository , JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository){
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(users);

        System.out.println(users.toString());


        int userTypeId = users.getUserTypeId().getUserTypeId();

        System.out.println(userTypeId);

        if(userTypeId == 1){
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));

        }

        else{
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }


        return users;
    }

    public Optional<Users> getUsersByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
