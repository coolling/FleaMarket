package Sever_mechine;

import Sever_mechine.Comment.*;
import Sever_mechine.Good.*;
import Sever_mechine.ShopCarr.*;
import Sever_mechine.TR.*;
import Sever_mechine.User.*;

import java.io.*;
import java.net.*;
import java.lang.*;

public class start {
    public static void main(String args[]){
        //采用接口创建多线程；
        //创建所有 User 开头类的线程对象；
        userLoginLine login = new userLoginLine();
        userRegisterLine register = new userRegisterLine();
        userInfoChangeLine infoChange = new userInfoChangeLine();
        userPassChangeLine passChange = new userPassChangeLine();
        userPictureChangeLine pictureChange = new userPictureChangeLine();
        userToPerLine toPer = new userToPerLine();

        //创建所有 Good 开头类的线程对象；
        goodAddLine goodAdd = new goodAddLine();
        goodGetLine goodGet = new goodGetLine();
        goodInfoGetLine goodInfoGet = new goodInfoGetLine();
        goodOffshoreLine goodOffshore = new goodOffshoreLine();
        goodSeeMineLine goodSeeMine = new goodSeeMineLine();
        goodSearchLine goodSearch = new goodSearchLine();
        goodBuyLine goodBuy = new goodBuyLine();

        //创建所有 ShopCar 开头类的线程对象；
        shopCarAddLine shopCarAdd = new shopCarAddLine();
        shopCarBuyLine shopCarBuy = new shopCarBuyLine();
        shopCarDeleteAllLine shopCarDeleteAll = new shopCarDeleteAllLine();
        shopCarDeleteSingleLine shopCarDeleteSingle = new shopCarDeleteSingleLine();
        shopCarSeeLine shopCarSee = new shopCarSeeLine();

        //创建所有 Comment 开头类的线程对象；
        commentAddLine commentAdd = new commentAddLine();
        commentSeeLine commentSee = new commentSeeLine();

        //创建所有 Tr 开头类的线程对象；
        TrReceiveLine TrReceive = new TrReceiveLine();
        TrSeeLine TrSee = new TrSeeLine();

        //创建所有 Message 开头累的线程对象；


        /*——————————————————————————分界线——————————————————————————*/

        //传递给所有 User 线程对象的构造函数；
        Thread toPerStart = new Thread(toPer);
        Thread pictureChangeStart = new Thread(pictureChange);
        Thread passChangeStart = new Thread(passChange);
        Thread loginStart = new Thread(login);
        Thread registerStart = new Thread(register);
        Thread infoChangeStart = new Thread(infoChange);

        //传递给所有 Good 线程对象的构造函数；
        Thread goodOffshoreStart = new Thread(goodOffshore);
        Thread goodInfoGetStart = new Thread(goodInfoGet);
        Thread goodGetStart = new Thread(goodGet);
        Thread goodAddStart = new Thread(goodAdd);
        Thread goodSeeMineStart = new Thread(goodSeeMine);
        Thread goodSearchStart = new Thread(goodSearch);
        Thread goodBuyStart = new Thread(goodBuy);

        //传递给所有 ShopCar 线程对象的构造函数；
        Thread shopCarSeeStart = new Thread(shopCarSee);
        Thread shopCarDeleteSingleStart = new Thread(shopCarDeleteSingle);
        Thread shopCarDeleteAllStart = new Thread(shopCarDeleteAll);
        Thread shopCarBuyStart = new Thread(shopCarBuy);
        Thread shopCarAddStart = new Thread(shopCarAdd);

        //传递给所有 Comment 线程对象的构造函数；
        Thread commentSeeStart = new Thread(commentSee);
        Thread commentAddStart = new Thread(commentAdd);

        //传递给所有 Tr 线程对象的构造函数；
        Thread TrSeeStart = new Thread(TrSee);
        Thread TrReceiveStart = new Thread(TrReceive);

        //传递给所有 Message 线程对象的构造函数；

        /*——————————————————————————分界线——————————————————————————*/

        //启动所有线程
        registerStart.start();
        loginStart.start();
        passChangeStart.start();
        pictureChangeStart.start();
        infoChangeStart.start();
        toPerStart.start();
        goodAddStart.start();
        goodGetStart.start();
        goodInfoGetStart.start();
        goodOffshoreStart.start();
        goodSeeMineStart.start();
        goodSearchStart.start();
        goodBuyStart.start();
        shopCarAddStart.start();
        shopCarBuyStart.start();
        shopCarDeleteAllStart.start();
        shopCarDeleteSingleStart.start();
        shopCarSeeStart.start();
        commentAddStart.start();
        commentSeeStart.start();
        TrReceiveStart.start();
        TrSeeStart.start();
    }

}
//对每个方法进行编写
//以下是有关User的方法；
class userLoginLine implements Runnable {
    public void run() {
        System.out.println("userLine 1 on !");
        while (true) {
            try {
                System.out.println("ok from userLine 1");
                User_login.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class userRegisterLine implements Runnable {
    public void run(){
        System.out.println("userLine 2 on !");
        while (true) {
            try {
                System.out.println("ok from userLine 2");
                User_register.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class userInfoChangeLine implements Runnable {
    public void run(){
        System.out.println("userLine 3 on !");
        while (true) {
            try {
                System.out.println("ok from userLine 3");
                User_info_change.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class userPassChangeLine implements Runnable {
    public void run(){
        System.out.println("userLine 4 on !");
        while (true) {
            try {
                System.out.println("ok from userLine 4");
                User_pass_change.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class userPictureChangeLine implements Runnable {
    public void run(){
        System.out.println("userLine 5 on !");
        while (true) {
            try {
                System.out.println("ok from userLine 5");
                User_picture_change.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class userToPerLine implements Runnable {
    public void run(){
        System.out.println("userLine 6 on !");
        while (true) {
            System.out.println("ok from userLine 6");
            try {
                User_toPer.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//User到此为止
//以下是有关Goods的方法；
class goodAddLine implements Runnable {
    public void run(){
        System.out.println("goodLine 1 on !");
        while (true) {
            System.out.println("ok from goodLine 1");
            try {
                Goods_add.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class goodGetLine implements Runnable {
    public void run(){
        System.out.println("goodLine 2 on !");
        while (true) {
            try {
                System.out.println("ok from goodLine 2");
                Goods_get.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class goodInfoGetLine implements Runnable {
    public void run(){
        System.out.println("goodLine 3 on !");
        while (true) {
            System.out.println("ok from goodLine 3");
            try {
                Goods_info_get.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class goodOffshoreLine implements Runnable {
    public void run(){
        System.out.println("goodLine 4 on !");
        while (true) {
            try {
                System.out.println("ok from goodLine 4");
                Goods_offshore.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class goodSeeMineLine implements Runnable {
    public void run(){
        System.out.println("goodLine 5 on !");
        while (true) {
            try {
                System.out.println("ok from goodLine 5");
                Goods_see_mine.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class goodSearchLine implements Runnable {
    public void run(){
        System.out.println("goodLine 6 on !");
        while (true) {
            try {
                System.out.println("ok from goodLine 6");
                Goods_search.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class goodBuyLine implements Runnable {
    public void run(){
        System.out.println("goodLine 7 on !");
        while (true) {
            try {
                System.out.println("ok from goodLine 7");
                Goods_buy.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//Good到此为止
//以下是有关 ShopCar 的类；
class shopCarAddLine implements Runnable {
    public void run(){
        System.out.println("shopLine 1 on !");
        while (true) {
            try {
                System.out.println("ok from shopLine 1");
                ShopCar_add.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class shopCarBuyLine implements Runnable {
    public void run(){
        System.out.println("shopLine 2 on !");
        while (true) {
            try {
                System.out.println("ok from shopLine 2");
                ShopCar_buy.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class shopCarDeleteAllLine implements Runnable {
    public void run(){
        System.out.println("shopLine 3 on !");
        while (true) {
            try {
                System.out.println("ok from shopLine 3");
                ShopCar_delete_all.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class shopCarDeleteSingleLine implements Runnable {
    public void run(){
        System.out.println("shopLine 4 on !");
        while (true) {
            try {
                System.out.println("ok from shopLine 4");
                ShopCar_delete_single.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class shopCarSeeLine implements Runnable {
    public void run(){
        System.out.println("shopLine 5 on !");
        while (true) {
            try {
                System.out.println("ok from shopLine 5");
                ShopCar_see_cars.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//ShopCar到此为止
//以下是有关Comment的方法；
class commentAddLine implements Runnable {
    public void run(){
        System.out.println("commentLine 1 on !");
        while (true) {
            try {
                System.out.println("ok from commentLine 1");
                Comments_add.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class commentSeeLine implements Runnable {
    public void run(){
        System.out.println("commentLine 2 on !");
        while (true) {
            try {
                System.out.println("ok from commentLine 2");
                Comments_see.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//Comment到此为止
//以下是有关Tr的方法；
class TrReceiveLine implements Runnable {
    public void run(){
        System.out.println("TrLine 1 on !");
        while (true) {
            try {
                System.out.println("ok from TrLine 1");
                Ts_receive.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class TrSeeLine implements Runnable {
    public void run(){
        System.out.println("TrLine 2 on !");
        while (true) {
            try {
                System.out.println("ok from TrLine 2");
                Ts_see.main();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//Tr到此为止
//以下是有关Message的方法；


//Message到此为止
