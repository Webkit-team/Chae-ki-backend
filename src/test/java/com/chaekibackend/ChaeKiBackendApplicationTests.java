package com.chaekibackend;

import com.chaekibackend.book.domain.interfaces.BookRepository;
import com.chaekibackend.book.domain.service.BookService;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeMemberRepository;
import com.chaekibackend.chellenge.domain.interfaces.ChallengeRepository;
import com.chaekibackend.users.domain.interfaces.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChaeKiBackendApplicationTests {
//    private static final Logger log = LoggerFactory.getLogger(ChaeKiBackendApplicationTests.class);
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private ChallengeRepository challengeRepository;
//    @Autowired
//    private UsersRepository usersRepository;
//    @Autowired
//    private ChallengeMemberRepository memberRepository;

//    @Test
//    void bookFetchAndSave() {
//        WebClient webClient = WebClient.create();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        Map<Integer, String> categories = new HashMap<>();
//        categories.put(798, "사회과학");
//        categories.put(51514, "심리");
////        categories.put(5247, "심리");
//        categories.put(70241, "자기계발");
//        categories.put(656, "인문학");
//        categories.put(51536, "종교/역학");
//        categories.put(1438, "역사");
//        categories.put(987, "과학");
//        categories.put(1, "소설/시/희곡");
//        categories.put(170, "경제경영");
//
//        for (Map.Entry<Integer, String> category : categories.entrySet()) {
//            Integer id = category.getKey();
//            String name = category.getValue();
//            String url =
//                    "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbchjw9561322001&QueryType=ItemEditorChoice&MaxResults=30&Cover=Big&Output=JS&Version=20131101&CategoryId=" + id;
//
//            AladinResponse.AladinTotalResponse res = webClient.get().uri(url).retrieve()
//                    .bodyToMono(AladinResponse.AladinTotalResponse.class)
//                    .block();
//            List<Book> books = res.getItem()
//                    .stream()
//                    .map(bookDto -> Book
//                            .builder()
//                            .name(bookDto.getTitle())
//                            .category(name)
//                            .writer(bookDto.getAuthor().split("\\(지은이\\)")[0].trim())
//                            .description(bookDto.getDescription())
//                            .likeCount(0)
//                            .publisher(bookDto.getPublisher())
//                            .imageUrl(bookDto.getCover().replace("cover200", "cover500"))
//                            .shopUrl(bookDto.getLink())
//                            .price(bookDto.getPriceStandard())
//                            .isbnCode(bookDto.getIsbn())
//                            .publishDate(bookDto.getPubDate())
//                            .build())
//                    .toList();
//
//            bookRepository.saveAll(books);
//        }
//    }
//
//    @Test
//    void saveDummyChallenge() {
//        List<Challenge> challenges = new ArrayList<>();
//        for (long bookNo = 802; bookNo <= 806; bookNo++) {
//            Optional<Challenge> opt = challengeRepository.findById(bookNo);
//            if (opt.isEmpty()) {
//                continue;
//            }
//            Challenge challenge = opt.get();
//
//            challenge.setEndDate(LocalDate.of(2024, 5, 20));
//            challenge.setStartDate(LocalDate.of(2024, 5, 20).minusDays(21));
//
//
//            challenges.add(challenge);
//        }
//
//        challengeRepository.saveAll(challenges);
//    }
//
//    @Test
//    void saveDummyMember() {
//        List<Integer> unos = List.of(
//                257,
//                258,
//                259,
//                260,
//                261,
//                262,
//                263,
//                264,
//                265,
//                266,
//                267,
//                268,
//                269,
//                270,
//                271,
//                272
//        );
//        Integer cno = 1103;
//        Optional<Challenge> copt = challengeRepository.findById(cno.longValue());
//        unos.forEach(uno -> {
//            Optional<Users> uopt = usersRepository.findById(uno.longValue());
//            if (uopt.isPresent()) {
//                ChallengeMember member = ChallengeMember.builder()
//                        .challenge(copt.get())
//                        .users(uopt.get())
//                        .build();
//
//                memberRepository.save(member);
//            }
//        });
//    }
}
