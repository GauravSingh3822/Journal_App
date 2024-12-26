package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Resp.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    private static final Logger logger= LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user=userService.findByUserName(userName);
            journalEntryRepository.save(journalEntry);
            JournalEntry saved=journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            user.setUserName(null);
            userService.saveNewUser(user);

        }catch(Exception e){

            throw new RuntimeException("An error occured while saving the entry.",e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }

public List<JournalEntry>getAll(){
        return journalEntryRepository.findAll();
}
public Optional<JournalEntry> findByID(ObjectId id){
        return journalEntryRepository.findById(id);
}
@Transactional
public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
    try {
        User user = userService.findByUserName(userName);

        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    }catch(Exception e){
        logger.info("hahahahaha");

        throw  new RuntimeException("An erro occured while deleting the entry .",e);
    }
    return removed;
}

}
