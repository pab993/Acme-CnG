package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select min(a.sendMessages.size) from Actor a")
	public Integer minMessagesSended();
	
	@Query("select max(a.sendMessages.size) from Actor a")
	public Integer maxMessagesSended();
	
	@Query("select avg(a.sendMessages.size) from Actor a")
	public Integer avgMessagesSended();
	
	@Query("select m from Message m where m.actorSender.id=?1 or m.actorRecipient.id=?1")
	public Collection<Message> findAllByActor(int actorId);
}
