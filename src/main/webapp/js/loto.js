function ApearNumCount(){
var request=new XMLHttpRequest();
var result=document.getElementById("ApearNumCountRul");
var text = '<table id="Numcount" class="pure-table pure-table-bordered "><tr>';

var count=1;
request.onreadystatechange = function() {
	if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
		 var obj =JSON.parse(this.responseText);
		 var lengthJason=getJsonObjLength(obj);
	 
		 if(lengthJason<5){
		       for(var j=0;j<lengthJason;j++)
			    	 text+="<th>號碼</th><th>次數</th></tr>"
		    }
		    else{
		      for(var j=1;j<6;j++)
			    	 text+="<th>號碼</th><th>次數</th>"
		    }
		     text+="</tr><tr>"
		 for(var key in obj){
			 if((count%5)==0){
			      text+="<td>"+key+"</td><td>"+obj[key]+"</td></tr><tr>";
			      count+=1;
			    }
			    else{
			      text+="<td>"+key+"</td><td>"+obj[key]+"</td>";
			      count+=1;
			    }
			   }
			    text += "</tr></table>";
			    result.innerHTML=text; 
			 
		   }
	}  
request.open("GET","countNum",true);
request.setRequestHeader("content-type","application/json");
request.send();


}
//取得jason的長度
function getJsonObjLength(jsonObj) {
	 var Length = 0; 
	 for (var item in jsonObj) {
		 Length++;
	} 
	 return Length;
}