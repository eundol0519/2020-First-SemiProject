package action;

import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import connection.weather;

public class WeatherAction{

	public int weather() throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = builder.parse("http://www.kma.go.kr/weather/forecast/mid-term-xml.jsp?stnId=109");

		NodeList list = doc.getElementsByTagName("location");

		weather w = new weather();

		for (int i = 1; i < 2; i++) {

			for (Node node = list.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {

				/////////////////////////////////////////////////////////////////////////////////////////////////

				if (node.getNodeName().equals("data")) {

					for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {

						if (node2.getNodeName().equals("city")) {

							node2.getTextContent();

						} else if (node2.getNodeName().equals("tmEf")) { // 날짜

							w.setA1(node2.getTextContent());

						} else if (node2.getNodeName().equals("wf")) { // 구름량

							w.setA2(node2.getTextContent());

						} else if (node2.getNodeName().equals("tmn")) { // 최저온도

							w.setA3(node2.getTextContent());

						} else if (node2.getNodeName().equals("tmx")) { // 최고온도

							w.setA4(node2.getTextContent());

						}

					}

				}

			}

		} // 1for
		return Integer.parseInt(w.getA4()) ;
	}
}