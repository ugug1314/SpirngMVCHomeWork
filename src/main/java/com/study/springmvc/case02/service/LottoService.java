package com.study.springmvc.case02.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	private List<Set<Integer>> lottos = new ArrayList<>();
	
	public List<Set<Integer>> getLottos() {
		return lottos;
	}
	
	public void addLotto() {
		//設定為0的位置，表示新增的資料都會在最前面
		lottos.add(0, generateLotto());
	}
	
	public void updateLotto(int index) {
		lottos.set(index, generateLotto());
	}
	
	public void deleteLotto(int index) {
		lottos.remove(index);
	}
	
	//產生樂透號碼
	private Set<Integer> generateLotto() {
		Random r = new Random();
		// 樂透 539: 1~39 取出不重複的5個號碼
		Set<Integer> lotto = new LinkedHashSet<>();
		while(lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1);
		}
		return lotto;
	}
	
	//統計數字出現的次數
		//統計數字出現的次數
		public Map<String,Integer> calNumapearCount() {
			Map<String,Integer> ApearNumCounts=new HashMap<>();
			lottos.stream().flatMapToInt(n->n.stream().mapToInt(v->v))
			.forEach(s->ApearNumCounts.merge(s+"",1,Integer::sum));
			System.out.println(ApearNumCounts);
			//lottos.stream().forEach(n->ApearNumCounts.computeIfPresent(n,(key,val)->val+1));
			return ApearNumCounts;
  }
}