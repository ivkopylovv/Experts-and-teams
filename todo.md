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

### GET /chats

Steps:
- Find all teams of user

Request:
```aidl
void
```

Response:

Status 200:
```aidl
TeamsResponse {
    teams: List<TeamResponse> {
        teamId: long,
        teamName: string,
        membersCount: long,
        captainId: long
}
```

### DELETE /leave-team
- if user is captain - delete team

Request:
```aidl
teamId
```
Response:

void

### POST /create-team
Request:
```
AddTeamRequest {
    name: string
}
```

Response:
```aidl
void
```

### GET /available-teams
Request:
```aidl
void
```

Response:

Status 200:
```aidl
TeamsResponse {
    teams: List<TeamResponse>
}
```

### POST /join-team
Request:
```aidl
TeamJoinRequest {
    teamId: long,
    message: string
```

Response:
```aidl
void
```

### GET /chat
Request
```aidl
    teamId: long
```

Response:
```aidl
TeamResponse {
    teamName: string,
    teamMessages: {
        username: string,
        expertName: string,
        message: string,
        date: Date
    }
}
```

### POST /send-message
- if message to expert - join expert in team

Request:
```aidl
SendMessageRequest {
    teamId: long,
    message: string,
    expertId: long
```

Response:
```aidl
void
```

### GET /available-experts

- Not blocked and not in this team and current_teams_count != max_teams_count

```aidl
AvailableExpertsRequest {
    teamId: long
}
```

```aidl
AvailableExpertsResponse{
    List<AvailableExpertResponse> : {
        expertId : long,
        expertName: name,
        skills : array<string>
}
```

### GET /team-join-requests

ВЕЗДЕ ПРОСТО ИМЯ И СВЕРХУ ТОЖЕ, А НЕ USERNAME

Request:
Достать юзера из фильтра

Response:
```aidl
TeamJoinsRequest {
    list<TeamJoinRequest> :
    requestId: long,
    name of user: long,
    message: string
}
```

### POST /join-request-decision
Request:
```aidl
JoinDesicion {
    requestId: long,
    isAccepted: boolean
```

Response:
```aidl
void
```

### GET /team-experts

Request:
```aidl
TeamExpertsRequest {
    teamId: long
}
```

Response:
```aidl
TeamExpertsResponse {
    list<TeamExpertResponse> {
        expertId: long,
        expertName: string,
        isBlocked: boolean
    }
 }
```


### POST /block-expert

Request
expertId
teamId

Response
void
