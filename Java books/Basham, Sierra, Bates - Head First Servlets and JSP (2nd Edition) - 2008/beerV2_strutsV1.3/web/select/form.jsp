<%@ taglib prefix="html"
           uri="http://struts.apache.org/tags-html" %>

<html>
<body>

<h1 align='center'>Beer Selection Page</h1>

<%-- Report any errors (if any) --%>
<html:errors />

Select beer characteristics<p>

<html:form action="/select/SelectBeer.do" method="POST"
           focus="color">

Color:
  <html:select property="color">
    <html:option value="light"> light </html:option>
    <html:option value="amber"> amber </html:option>
    <html:option value="brown"> brown </html:option>
    <html:option value="dark"> dark </html:option>
    <html:option value="red"> red </html:option>
  </html:select>
<br/><br/>

<center>
  <html:submit value="Select Beer Style" />
</center>

</html:form>

</body>
</html>
