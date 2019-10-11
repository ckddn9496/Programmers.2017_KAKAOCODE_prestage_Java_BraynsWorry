import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String sentence = "QQQQHaEaLaLaOAABbWORLDbABCDeDe"; // return HELLO WORLD
		
//		String sentence = "HaEaLaLaObWORLDb"; // return HELLO WORLD
		
//		String sentence = "SpIpGpOpNpGJqOqA"; // return SIGONG JOA
		
//		String sentence = "AxAxAxAoBoBoB"; // return invalid

		System.out.println(new Solution().solution(sentence));
	}

}

class Solution {
	private StringBuilder answer = new StringBuilder();

	public String solution(String sentence) {
		
		if (sentence.contains(" ")) // 규칙 3 위반
			return "invalid";

		makeRawSentence(sentence);
		return answer.toString();
	}
	
	private void makeRawSentence(String sentence) {
//		System.out.println("\nSENTENCE : " + sentence);
		List<Integer> list = new LinkedList<>();
		char symbol = 0;
		for (int i = 0; i < sentence.length(); i++) {
			char c = sentence.charAt(i);
			if (c >= 'a' && c <= 'z') {
				if (list.isEmpty()) {
					symbol = c;
				}
				if (symbol == c) {
					list.add(i);
				} else {
					break;
				}
			}
		}
		
		if (list.size() == 0) {
			answer.append(sentence); // 소문자가 더 이상 없을 경우 answer에 붙이기 (맨마지막)
		} else {
			int start = list.get(0);
			int end = list.get(list.size()-1);
			
			if (list.size() == 2) {
				// 규칙 2
//				System.out.println("RULE TWO");
//				System.out.println("[" + start + ", " + end + "]");
				String preWord = sentence.substring(0, start);
//				System.out.println("PRE " + preWord);
				String rawWord = sentence.substring(start+1, end);
//				System.out.println("RAW " + rawWord);
				String postWord = sentence.substring(end+1);
//				System.out.println("POST " + postWord);
				
				if (preWord.length() == 0) 
					answer.append(rawWord+ " ");
				else 
					answer.append(preWord + " " + rawWord+ " ");
				makeRawSentence(postWord);
			
			} else {
				// 규칙 1
				if (end+2 > sentence.length() || start == 0) {
					answer = new StringBuilder("invalid");
					return;
				} else {
//					System.out.println("RULE ONE");
//					System.out.println(list.toString());
					String preWord = sentence.substring(0, start-1);
//					System.out.println("PRE " + preWord);
					String rawWord = sentence.substring(start-1, end+2).replaceAll("[a-z]", "");
//					System.out.println("RAW " + rawWord);
					String postWord = sentence.substring(end+2);
//					System.out.println("POST " + postWord);
					
					if (preWord.length() == 0) 
						answer.append(rawWord+ " ");
					else 
						answer.append(preWord + " " + rawWord + " ");
					makeRawSentence(postWord);
				}
			}
		}
		
	}
}
