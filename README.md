# SpirngMVCHomeWork

<b>題目
2022/02/13-
在showlotto.jsp中統計每組樂透號碼數字出現的次數並依次數大到小排序<br>
程式碼1:[LottoService.java](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case02/service/LottoService.java)<br>
程式碼2:[showlotto.jsp](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/webapp/WEB-INF/views/case02/show_lotto.jsp)<br>
程式碼3:[lotto.js](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/webapp/js/loto.js)<br>
  <b>題目
2022/02/20-<br>
(1)在exam_form將考試時段的部分由多個<spform:checkbox>，改用<spform:checkboxes>來呈現<br>
(2)在exam_form將繳費的<spform:radiobutton>，改用<spform:radiobuttons>來呈現<br>
程式碼1:[新增ExamTime類別](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case03/entity/ExamTime.java)<br>
程式碼2:[新增ExamPay類別](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case03/entity/ExamPay.java)<br>
程式碼3:[在service中新增examTimes及examPays的集合並加入資料，再新增queryExamSubjectList及queryExamTimesList方法](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case03/service/ExamService.java)<br>
程式碼4:[修改exam_form.jsp中的相關程式碼28~44行](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/webapp/WEB-INF/views/case03/exam_form.jspf)

<自行加強-在點選case01中HelloController下的方法時，一併將程式碼顯示於下方><br>
2022/4/4<br>
程式碼1:[在springmvc-servlet.xml檔新增<aop:aspectj-autoproxy></aop:aspetj-autoproxy>](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/webapp/WEB-INF/springmvc-servlet.xml)<br>
程式碼2:[在HelloController中各方法之間加入#號當作識別記號](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case01/controller/HelloController.java)<br>
程式碼3:[新增GetcodesAspect切面類別](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case01/entity/GetcodesAspect.java)<br>
程式碼4:[新增AopConfig設定檔註冊切面程式](https://github.com/ugug1314/SpirngMVCHomeWork/blob/main/src/main/java/com/study/springmvc/case01/entity/AopConfig.java)<br>
