// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: TRIPLE_QUOTE_OPEN empty multiline string - verifies that triple-quoted empty string is correctly parsed
 *              as an empty string literal with proper content and length
 */
package syntax.grammar.p111.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    // 1. 验证空三引号字符串的内容
    val emptyTripleQuote = """"""
    
    // 2. 验证长度（空字符串长度应为 0）
    if (emptyTripleQuote.length != 0) {
        return "NOK"
    }
    
    // 3. 验证内容（应该是空字符串）
    if (emptyTripleQuote != "") {
        return "NOK"
    }
    
    // 4. 验证类型（应该是 String）
    if (emptyTripleQuote !is String) {
        return "NOK"
    }
    
    return "OK"
}
