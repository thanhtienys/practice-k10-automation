package models.components.global.block;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(".master-wrapper-main")
public class BlockComponent extends Component {

    public BlockComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Categories categories() {
        return findComponent(Categories.class, driver);

    }

    public List<SublistComponent> sublistComponent(){
        return findComponents(SublistComponent.class,driver);
    }



//
//    public Manufacturers manufacturers(){
//        return findComponent(Manufacturers.class, driver);
//
//    }
//
//    public PopularTags categoryBox(){
//        return findComponent(PopularTags.class, driver);
//
//    }
//
//    public NewsLetter newsLetter (){
//        return findComponent(NewsLetter.class, driver);
//
//    }
//
//    public RecentlyViewedPrducts recentlyViewedPrducts (){
//        return findComponent(RecentlyViewedPrducts.class, driver);
//
//    }
//
//    public CommunityPoll communityPoll (){
//        return findComponent(CommunityPoll.class, driver);
//
//    }
}
