<%-- 
    Document   : contractReportTemplate
    Created on : 2014-01-08, 09:58:24
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body onLoad="window.print(); window.location='/CMS/reportPrint.htm#/reportPrint.htm';">
	<center>
        <h1>Umowa numer ${contractNumber}</h1>
	</center>
        Pracownik zawierający umowę: ${empName} ${empSurname}
		<br/>
		Klient zawierający umowę: ${cusName} ${cusSurname}
		<br/>
		Treść umowy:
		${desc}
		<br/>
                Cena usług: ${price}
                <br/>
		Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pharetra metus justo, id vestibulum urna venenatis eget. Cras aliquet volutpat nulla. Duis euismod nibh sit amet dui consequat, cursus commodo est rhoncus. Vivamus vitae commodo lectus. Quisque suscipit rutrum nibh in faucibus. Sed ut commodo velit. Nunc risus ipsum, porttitor non vestibulum in, blandit congue erat. Aliquam aliquam varius leo.

		Vivamus pharetra ultricies purus. Vestibulum in imperdiet ligula. In et turpis ullamcorper, ullamcorper lacus non, dictum mauris. Quisque bibendum, justo sit amet ornare tincidunt, nulla odio gravida metus, ac rutrum nulla est at lorem. Vivamus augue felis, facilisis eget fringilla imperdiet, convallis quis tortor. Praesent euismod libero mauris, non tempus nunc mattis eget. Donec lacinia ante ut sodales mollis. Nam vitae nisi ac purus interdum pellentesque vitae non diam. Suspendisse potenti.

		Fusce sed ante cursus, venenatis lectus at, pulvinar erat. Curabitur non ante eget odio consequat sodales ac eget turpis. Etiam lacinia blandit nisi id semper. Vestibulum lacinia mi vel dapibus tincidunt. Nam augue nibh, interdum nec diam ut, dignissim semper dolor. Pellentesque fringilla id neque id tincidunt. Cras vestibulum dui vitae ipsum auctor euismod. Ut fermentum, lorem a tristique scelerisque, sapien lorem porttitor enim, nec pharetra velit tortor eu erat. Vivamus dapibus ipsum dapibus tellus convallis sollicitudin. Nullam sapien nisl, semper et convallis sed, volutpat eu sapien. Nunc non pretium felis. Vestibulum arcu enim, placerat eget aliquam luctus, consequat eleifend erat. Vivamus consectetur sagittis commodo.

		Vivamus condimentum sapien in sapien rutrum, eu rutrum mauris dictum. Duis lobortis sollicitudin eros, id mollis augue blandit in. Nam quis feugiat enim, quis gravida leo. Etiam volutpat imperdiet lacus eget feugiat. Praesent semper non massa quis gravida. Fusce imperdiet, sapien id aliquam convallis, nulla sapien ultricies orci, vel condimentum sem mi sit amet felis. Suspendisse ultricies neque quis porttitor facilisis. Vestibulum eleifend tincidunt nisi. Curabitur dapibus, arcu eget imperdiet pharetra, arcu velit fringilla leo, vitae faucibus dui ante id neque. Etiam sed imperdiet urna, vehicula sollicitudin ipsum. Aliquam odio justo, egestas a volutpat sed, viverra id massa. Sed ut ante tempor, pretium odio non, vestibulum purus. Phasellus laoreet massa in nulla vestibulum, vel malesuada tortor gravida. Morbi quis nunc laoreet, viverra nisi eu, posuere eros. Aliquam vehicula purus mi, ac eleifend felis pharetra quis.

		Duis dictum nunc at aliquam iaculis. Curabitur tempor sapien sed bibendum adipiscing. Aenean molestie, ligula ut semper dictum, purus est aliquet justo, sed vestibulum diam enim vel massa. Nullam rutrum risus et dolor sodales, quis consequat sem elementum. Quisque ut rutrum lacus. Aliquam viverra arcu ligula, non vestibulum lacus ultrices eget. Pellentesque mollis leo id tristique luctus. In blandit eros eros, quis rhoncus est fermentum ac. Curabitur ac ornare odio. Maecenas ultrices pellentesque viverra. Quisque eget ipsum scelerisque, viverra leo ac, sagittis mauris. Etiam pretium urna eget nunc porttitor volutpat. Nulla viverra tempus odio, non porta neque adipiscing id. Aenean feugiat dapibus sollicitudin. Pellentesque a dui sapien.
    </body>
</html>
