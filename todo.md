# Sign UP

### POST /signup

Steps:
- Check user existing
- Create user if not exist
- Save user skills if user is expert

Request:
```
SignUpRequest {
    name: string,
    username: string,
    password: string,
    role: string,
    skills: Array<string> | null
}
```

Response:

Status 400:
```
ErrorResponse {
    message: string
}
```

Status 201:
```
{}
```

# Sign In

### POST /signin

Steps:
- Check user existing
- Validate password
- Create session if user exist
- Create cookie with user id

Request:
```
SignInRequest {
    username: string,
    password: string
}
```

Response:

Status 400:
```
ErrorResponse {
    message: string
}
```

Status 200:
```
{}
```

# Admin Dashboard

### POST /admin-dashboard/add-user

Steps:
- Check user existing
- Create user if not exist
- Save user skills if user is expert

Request:
```
AddUserRequest {
    name: string,
    username: string,
    password: string,
    role: string,
    skills: Array<string> | null
}
```

Response:

Status 400:
```
ErrorResponse {
    message: string
}
```

Status 201:
```
UserResponse {
    name: string,
    username: string,
    password: string,
    role: string,
    skills: Array<string> | null,
    isOnline: boolean,
    isBlocked: boolean 
}
```

### POST /admin-dashboard/edit-user

Steps:
- Check existing of user
- Edit user fields
- Query edit user
- Return edited user

Request:
```
EditUserRequest {
    id: long,
    name: string,
    username: string,
    password: string
}
```

Response:

Status 200:
```
UserResponse {
    name: string,
    username: string,
    password: string,
    role: string,
    skills: Array<string> | null,
    isOnline: boolean,
    isBlocked: boolean 
}
```

### POST /admin-dashboard/delete-user

Request:
```
DeleteUserRequest {
    userId: long
}
```

Response:

Status 200:
```
{}
```

Status 400:
```
ErrorResponse {
    message: string
}
```

### GET /admin-dashboard/users

Steps:
- Return users list except current user

Response:

Status 200:
```
UsersResponse {
    users: Array<UsersResponse>
}
```

# Moderator Dashboard

### GET /moderator-dashboard/users

Steps:
- Return users list except current user

Response:

Status 200:
```
UsersResponse {
    users: Array<UsersResponse>
}
```

### POST /moderator-dashboard/block-users

Steps:
- Toggle block status of users

Request:
```
BlockUsersRequest {
    userIds: Array<Long>
}
```

Response:

Status 200:
```
{}
```

# User Dashboard

### GET /user-dashboard/experts
