package com.serwylo.lexica.trie;

import net.healeys.trie.Trie;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TrieBuilder {

	private final File usDictFile;
	private final File ukDictFile;
	private final File outputFile;

	public TrieBuilder(File usDictFile, File ukDictFile, File outputFile) {
		this.usDictFile = usDictFile;
		this.ukDictFile = ukDictFile;
		this.outputFile = outputFile;
	}

	public void run() throws IOException {
		Trie outTrie = new Trie();

		readFileIntoTrie(usDictFile, outTrie, true, false);
		readFileIntoTrie(ukDictFile, outTrie, false, true);

		FileOutputStream of = null;
		try {
			of = new FileOutputStream(outputFile, false);
			outTrie.write(new DataOutputStream(of));
		} finally {
			if(of != null) {
				of.close();
			};
		}
	}

	private static void readFileIntoTrie(File dictFile, Trie trie, boolean usWord, boolean ukWord) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(dictFile));
		String line;
		while((line = br.readLine()) != null) {
			trie.addWord(line, usWord, ukWord);
		}
	}

}
