package projekti.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author karpo
 */
@Data
@NoArgsConstructor @AllArgsConstructor
public class FoundUser {
    
    private String name;
    private String username;
    private String profile;
    private boolean kick;
    private boolean accept;
    private boolean cancel;
    private boolean propose;
}
