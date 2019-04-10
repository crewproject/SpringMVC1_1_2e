import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mvc.app.board.service.BoardService;
import com.mvc.app.data.BBSVO;
import com.mvc.app.data.SearchType;


/*
 * BoardServiceTest에서 boardService는 {@link com.mvc.app.BoardService}를 나타낸다.
 *
 * @see {@link com.mvc.app.BoardService}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring/**/*-context.xml" })
public class BoardServiceTest {

	@Inject
	BoardService bs;
	
	@Test @Ignore
	public void testGetSearchListBBS() {
		
		List<BBSVO> bbsList = bs.getSearchListBBS(SearchType.COMMENT, "update");
		System.out.println("bbsList : "+bbsList);
		printBBSList(bbsList);
	}

	// boardService의 createBBS()를 테스트한다.
	@Test @Ignore
	public void testCreateBBS() {
		BBSVO vo = new BBSVO(
				"test", 		//name
				"testEmail",	//email
				"testPass",		//pass
				"testTitle",	//title
				"testComment",	//comment
				"testIP"		//ip
				);
		bs.createBBS(vo);
	}

	// boardService의 getListBBS()를 테스트한다.
	@Test 
	@Ignore
	public void testGetListBBS() {
		List<BBSVO> bbsList = bs.getListBBS();
		printBBSList(bbsList);
	}

	// boardService의 getOneBBS()를 테스트한다.
	@Test @Ignore
	public void testGetOneBBS() {
		BBSVO vo = bs.getOneBBS("1");
		System.out.println("=========boardServiceTest - getOne Test============");
		System.out.println(vo);
		System.out.println("id : " + vo.getId());
		System.out.println("name : " + vo.getName());
		System.out.println("pass : " + vo.getPass());
		System.out.println("title : " + vo.getTitle());
		System.out.println("comment : " + vo.getComment());
		System.out.println("wdate : " + vo.getWdate());
		System.out.println("see : " + vo.getSee());
	}
	
	//boardService의 modifyBBS()를 테스트한다.
	@Test @Ignore
	public void testModifyBBS() {
		BBSVO vo = new BBSVO(
				2,					//id
				"update title",		//title
				"update comment"	//comment
				);
		bs.modifyBBS(vo);
	}

	// boardService의 removeBBS()를 테스트한다.
	@Test @Ignore
	public void testRemoveBBS() {
		String id = "12";
		bs.removeBBS(id);
	}
	
	public void printBBSList(List<BBSVO> bbsList) {
		System.out.println("=========boardServiceTest - getList Test============");
		for (BBSVO vo : bbsList) {
			System.out.println("id : " + vo.getId());
			System.out.println("name : " + vo.getName());
			System.out.println("title : " + vo.getTitle());
			System.out.println("wdate : " + vo.getWdate());
			System.out.println("see : " + vo.getSee());
			System.out.println("=====================");
		}
	}
}
