import { Button } from "@mui/material";
import styles from "./css/Item.module.css";

export default function Item(props){
    const {
        id,
        name,
        image_link,
        price,
        description,
        updated_at,
        category,
        product_type,
        product_link
    } = props.item; // 인자로 넘어온 item객체를 저장한다.
    return(
        <>
            <div className={styles.wrap}>
                <div className={styles.img_item}>
                    <img src={image_link} alt={name}/>
                </div>
                <div className={styles.info_item}>
                    <strong className={styles.tit_item}>{name}</strong>
                    <strong className={styles.num_price}>{price}</strong>
                    <span className={styles.txt_info}>
                        {/* 카테코리가 있을 때는 출력하고 없으면 공백 출력 */}
                        {category? `${category}/` : ""}
                        {product_type}
                    </span>
                    <Button variant="contained" color="error">구매하기</Button>
                </div>
            </div>
        </>
    );
};