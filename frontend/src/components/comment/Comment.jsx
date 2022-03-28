import { useState } from "react";
import styles from "./Comment.module.css"
import Button from '@mui/material/Button';
import axios from "axios";
import CommentList from "./CommentList";
import cheer from "../../assets/cheer.png";
import { useSelector } from 'react-redux'



function Comment({candiId, data, renderCheck}) {

    const [ripple, setRipple] = useState("")
    const token = useSelector((state)=>(state[0].token));
    const nick = useSelector((state)=>(state[0].token));

    function changeInput(e){
        setRipple(e.target.value)
    }

    function onSubmit(e){
        e.preventDefault()
       
        axios
        .post(
            "http://j6a304.p.ssafy.io:8080/api/polls/candidates/comments",
            {
                "candidateId": candiId,
                "content": ripple
            },
            {
                headers: {
                    Authorization: token
                },
            }
        )
        .then((res) =>{
            console.log("댓글작성!")
            setRipple("")
            renderCheck()
            
        })
        .catch((e) =>{
            console.error(e);
        });

    };

    return (
        <div className={styles.comment_box}> 
            <div id={styles.message}><img id={styles.cheer} src={cheer} alt="cheer"/>응원 메시지</div>
            <form id={styles.comment_form} onSubmit={onSubmit}>
                <textarea id={styles.comment_input} type="text" onChange={changeInput} value={ripple} name="ripple" maxLength="100"
                    placeholder="응원 메시지를 적어주세요~!"
                ></textarea>
                <Button id={styles.register_button} type="submit" variant="contained">댓글 등록</Button>
            </form>
            <CommentList data={data} renderCheck={renderCheck}></CommentList>
        </div>
    );
}

export default Comment;