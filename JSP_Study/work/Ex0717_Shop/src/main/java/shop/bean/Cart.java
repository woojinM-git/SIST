package shop.bean;

import mybatis.vo.ProductVO;

import java.util.ArrayList;
import java.util.List;

public class Cart {
/*
    카트(장바구니)의 기능분석
    1) 제품(ProductVO)을 저장할 수 있는 공간 : List<ProductVO>
    2) 1)에서 제품을 검색하는 기능(저장할 때도 필요한 기능)
    3) 1)에 제품을 저장하는 기능
    4) 1)에 제품을 삭제하는 기능
    5) 2)에서 검색된 제품의 수량을 변경하는 기능
    6) 1)에 저장된 모든 제품들의 총액 계산
    7) 1)에 저장된 모든 제품들을 배열로 반환하는 기능

    위 분석에 따른 장바구니의 속성(멤버변수)
        -List<ProductVO>
        -int 또는 long 형으로 totalPrice;
        
    위 기능분석을 바탕으로 장바구니 기능(멤버메서드) 설계
        - searchProduct : 제품검색
        - addProduct : 제품추가(저장)
        - delProduct : 제품삭제
        - changeCount : 수량변경
        - getList : 장바구니에 있는 모든 제품들을 배열로 반환하는 기능
*/
    private List<ProductVO> list; // 장바구니의 저장소
    private long totalPrice; // 총액

    public Cart(){ // 생성자 - useBean 정의시 자동 호출됨
        list = new ArrayList<>();
    }
    
    // 장바구니의 저장소(list)에서 특정 제품(ProductVO)을 검색하는 기능
    public ProductVO searchProduct(String p_num){
        ProductVO vo = null;
        
        // 제품들은 모두 list에 있으므로 list가 비었다는 것은
        // 사용자가 장바구니에 아무것도 담지 않았다는 뜻
        for(ProductVO pvo : list){
            if(pvo.getP_num().equals(p_num)){ // 객체를 반환하기때문에 == 사용불가
                // 찾고자 하는 제품을 찾은 경우 이때 pvo를 vo에 저장한 후 탈출
                vo = pvo;
                break; // 가장 가까운 반복문 탈출
            }
        } // for end
        
        return vo;
    }
    
    // 장바구니에 있는 모든 제품들을 배열로 반환하는 기능
    public ProductVO[] getList(){
        ProductVO[] ar = null;

        // list가 비었을 때는 하지 않아야 한다.
        if(!list.isEmpty()){ // 비어있지 않을 때
            ar = new ProductVO[list.size()];
            list.toArray(ar);
        }
        return ar;
    }
    
    // p_list가 실제 마트의 진열대와 같다. 이 진열대에 제품을 가져와서 장바구니에 저장
    public void addProduct(ShopBean sb, String p_num) {
        // 가져온 제품이 장바구니에 이미 저장되었는지? 확인하자
        ProductVO vo = searchProduct(p_num); // vo가 null이 아니면 장바구니에
        // 이미 저장한 제품이다.

        if(vo != null){
            // 수량만 1 증가 시킨다.
            // 그러기 전에 기존의 수량을 먼저 가져온다.
            int q = vo.getQuant();
            vo.setQuant(q+1);
            return; // 수량을 증가했으므로 더이상 하지 않고. 제어권 반환
        }
        // ============================================================

        // ============장바구니에 원하는 제품이 없다면 추가=================
        // 인자로 넘어온 pnum을 ShopBean에게 줘서
        // 해당 제푸(ProductVO) 을 얻어낸다.

        // 진열대에서 원하는 제품을 가져온다.
        vo = sb.getProduct(p_num);

        vo.setQuant(1);

        // 수량이 1로 조정된 제품을 이제 장바구니에 저장한다.
        list.add(vo);
    }

    // 장바구니에서 특정 제품을 삭제하는 기능
    public boolean delProduct(String pnum){
        boolean value = false;

        // 삭제하기 전에 장바구니에서 검색하자
        ProductVO vo = searchProduct(pnum);
        if(vo != null){
            list.remove(vo); // 장바구니에서 삭제
            value = true;
        }
        return value;
    }
    
    // 장바구니에서 특정 제품을 검색한 후 수량만 변경하는 기능
    public void changeCount(String pnum, int q){
        // 현재 장바구니에서 검색
        ProductVO vo = searchProduct(pnum);
        if(vo != null)
            vo.setQuant(q);
    }
}

