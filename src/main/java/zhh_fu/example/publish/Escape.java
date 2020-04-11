package zhh_fu.example.publish;

import lombok.extern.slf4j.Slf4j;
import zhh_fu.annotations.NotRecommend;
import zhh_fu.annotations.UnSafeThread;

@Slf4j
@UnSafeThread
@NotRecommend
public class Escape {
    private int thisCanbeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}",Escape.this.thisCanbeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
