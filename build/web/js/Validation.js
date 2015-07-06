function date_regex_validation(date){
    alert(date)
    var date_regex= /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))$/;
    if(date_regex.test(date)){
        return true;
    }else{
        return false;
    }
    
}

function notnull_validation(el){
    alert(el);
    if(el!=""){
        return true;
    }else{
        return false;
    }
}

function radio_validation(radio){
    alert(radio.length)
    for(var i=0, length=radio.length;i<length; i++){
            if(radio[i].checked){ 
                return true;
                break;
            }
        }
    
}

function positive_number_validation(number){
    alert(number);
    if(number>0 && !isNaN(parseInt(number))){
        return true;
    }else{
        return false;
    }
}

function notnegative_number_validation(number){
    alert(number);
    if(number>=0 && !isNaN(parseInt(number))){
        return true;
    }else{
        return false;
    }
}

function datetime_regex_validation(datetime){
    alert(datetime);
    var datetime_regex= /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31)) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}$/;
    if(datetime_regex.test(datetime)){
        return true;
    }else{
        return false;
    }
}


function email_val(mail){
    
    
    
    var mail_regex=/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
    if(mail_regex.test(mail)){
        
        return true;
    }else{
        
        
        return false;
    }
            
            
            
    
    
}


function  password_valid(pass){
    
    
    var pass_reg=/(?=^.{6,}$)((?=.*[A-Z])|(?=.*[a-z])|(?=.*[0-9])).*$/;
    
    if(pass_reg.test(pass)){
        
        alert('true');
        return true;
    }
    else{
        
        return false;
    }
    
    
}


function style_inp(titleaddtask){
    
    
    
    document.getElementById(titleaddtask).removeAttribute("style");
    
    
}




function style_abstract_valid(tag){
    
    
    document.getElementById(tag).style.border = "1px solid #FF0000";

    document.getElementById(tag).style.boxShadow = "0 0 5px #FF0000";

    document.getElementById(tag).style.background = "#fff url(./img/invalid.png) 98% center no-repeat";

    document.getElementById(tag).style.backgroundSize = "22px 22px";
    
    
    
}