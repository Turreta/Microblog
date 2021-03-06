/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package us.repasky.microblog.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import us.repasky.microblog.domain.Post;

/**
 * This class uses <a href="http://www.springsource.org/spring-data/jpa">Spring Data</a> to expose Post entities from JPA.
 *
 * @author Drew Repasky
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
	
	@Transactional(readOnly=true)
	@Query("select p from Post p where p.blogUser.username in ?1 and p.createdDate > ?2 order by p.createdDate asc")
	List<Post> findByUsernameIn(List<String> usernames, Date createdAfter);
	
	@Transactional(readOnly=true)
	@Query("select p from Post p where p.blogUser.username in ?1 order by p.createdDate desc")
	Page<Post> findByUsernameIn(List<String> usernames, Pageable pageable);
	
}
