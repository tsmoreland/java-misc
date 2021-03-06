//
// Copyright © 2021 Terry Moreland
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
// to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
// and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
package moreland.spring.sample.jpademo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import moreland.spring.sample.jpademo.entities.Province;
import moreland.spring.sample.jpademo.projections.ProvinceFullname;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Optional<Province> findFirstByName(String name);
    Page<Province> findByNameContains(String name, Pageable pageable);
    Page<Province> findByCountryName(String countryName, Pageable pageable);

    @Query("""
        select p from Province p 
        join p.cities c
    """) // no join fetch due to paging even though cities is lazy loaded (eagerly here)
    Page<Province> getWithCities(Pageable pageable);

    @Query("""
        select distinct p from Province p
        join fetch p.cities 
        where p.id = :id
    """)
    Optional<Province> getWithCitiesById(@Param("id") Long id);

    Page<Province> findByCountryNameInOrderByNameAsc(List<String> countryNames, Pageable pageable);
    Page<Province> findByCountryNameInOrderByNameDesc(List<String> countryNames, Pageable pageable);

    @Query("""
        select distinct p from Province p
        where p.name = :name and p.countryId = :countryId
    """)
    Optional<Province> findByNameAndCountryId(@Param("name") String name, @Param("countryId") Long countryId);

    Optional<ProvinceFullname> getFullNameById(@Param("id") Long id);
    List<ProvinceFullname> getFullnames();
}
