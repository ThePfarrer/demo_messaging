package com.example.demo.security

import jakarta.persistence.*
import java.util.*

@Entity
@DiscriminatorValue("MEMBER")
class Member(
    id: String,
    email: String,
    pwd: String,
    firstName: String,
    lastName: String,
    roles: String,
    enabled: Boolean,
    accountNonExpired: Boolean,
    accountNonLocked: Boolean,
    credentialsNonExpired: Boolean,
    created: Date,
    modified: Date
) : User(
    id,
    email,
    pwd,
    firstName,
    lastName,
    roles,
    enabled,
    accountNonExpired,
    accountNonLocked,
    credentialsNonExpired,
    created,
    modified
) {

    constructor() : this(
        "", "", "", "", "",
        "", true, true, true, true, Date(), Date()
    )

}