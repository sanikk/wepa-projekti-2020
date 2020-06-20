/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Account;

/**
 *
 * @author karpo
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
