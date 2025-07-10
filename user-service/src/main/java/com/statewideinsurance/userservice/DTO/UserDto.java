package com.statewideinsurance.userservice.DTO;


import com.statewideinsurance.userservice.model.User;
import lombok.*;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public class UserDto {
        private Long id;
        private String fullName;
        private String email;

        private String phone;

        public User toUser() {
            return new User(id, fullName, email, phone);


            //You're trying to map a UserDto (from an external API response) to a User via a toUser() method
            // — which neither exists nor makes sense if you’re not fetching from an external service.
            // so need userdto also
        }
    }

