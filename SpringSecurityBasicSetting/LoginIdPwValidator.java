@Service
public class LoginIdPwValidator implements UserDetailsService {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA512PasswordEncoder();
    }
    
    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String insertedId) throws UsernameNotFoundException {
        UserInfo user = mapper.getUserInfo(insertedId);
        
        if (user == null) {
            return null;
        }
        
        String pw = user.getPw(); //"d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db"
        String roles = user.getRoles(); //"USER"

        return User.builder()
                .username(insertedId)
                .password(pw)
                .roles(roles)
                .build();
    }
}
