# Programmers.2017_KAKAOCODE_prestage_Java_BraynsWorry

## 프로그래머스 > 코딩테스트 연습 > 2017 카카오코드 예선 > 브라이언의 고민

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/1830

input으로 문자열 `sentence`가 들어온다. 대문자와 소문자가 섞여 있으며 규칙에 따라 바뀐 `sentence`를 원상태로 복구하는 문제이다. 원본 단어에서 세가지 규칙을 적용하여 `sentence`가 만들어 지는데 그 규칙은 이렇다. 

#### 규칙 1)

특정 단어를 선택하여 글자 사이마다 같은 기호를 넣는다

#### 규칙 2)

특정 단어를 선택하여 단어 앞뒤에 같은 기호를 넣는다

#### 규칙 3)

원래 문구에서 공백을 모두 삭제한다.

소문자는 기호이며, 대문자는 원래의 문자이다. 위 세가지 규칙으로 만들어진 `sentence`의 원래 문자열을 return하는 문제.

### 2. 풀이

`sentence`의 첫 인덱스부터 끝까지 한번의 탐색으로 원래의 문자열을 복구하고자 하였다. 기호가 처음 등장하면 다른 기호가 등장하거나 마지막 인덱스까지 탐색을 계속한다. 탐색이 끝나면 해당 기호에 대하여 처음과 끝 `index`를 알게 되는데 이 `index`의 규칙에 따라 `규칙 1`과 `규칙 2`의 적용여부를 따진다. 첫번째, 기호가 두번 등장하였으면 `규칙 2`를 적용하고 한번 또는 세번이상 등장하면 `규칙 1`을 적용한다. 두번째 적용하려는 규칙에 따라 규칙이 적용되는 범위의 문자열과 이전의 문자열, 이후의 문자열로 구간을 나눈다. (`preWord`, `rawWord`, `postWord`). 규칙이 적용된 문자열에 대해 기호를 제거하여 원래의 문자열로 만들어준 후 `preWord rawWord`를 출력할 문자열에 추가한다. 만약 `preWord`가 존재하지 않는다면 `rawWord`만 추가한다. 남은 `postWord`에 대해 위의 과정을 반복한다. `postWord`에 기호가 존재하지 않으면 그대로 문자열에 추가한후 탐색을 종료하고, 길이가 0이라면 탐색을 종료한다.

마지막으로 ,위의 과정으로 만들어진 문자열을 출력하여 문제를 해결하였다.

```java

private void makeRawSentence(String sentence) {
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
      String preWord = sentence.substring(0, start);
      String rawWord = sentence.substring(start+1, end);
      String postWord = sentence.substring(end+1);

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
        String preWord = sentence.substring(0, start-1);
        String rawWord = sentence.substring(start-1, end+2).replaceAll("[a-z]", "");
        String postWord = sentence.substring(end+2);

        if (preWord.length() == 0) 
          answer.append(rawWord+ " ");
        else 
          answer.append(preWord + " " + rawWord + " ");
        makeRawSentence(postWord);
      }
    }
  }

}

```

### 3. 카카오의 해결방법

문자열을 한번 탐색하며 기호에 대한 정보를 모은다. 기호와 그에 대한 인덱스를 조사한 후, 기호마다의 인덱스의 개수로 규칙을 제거하여 원본 문구를 만들어 내는 것이다. 문자열을 한번의 탐색과정에서 답을 내기위해 다른 방법을 사용했었지만, 재귀함수가 없는 것이 시간이나 공간적으로 훨씬 모범적인 답인것 같다. 


