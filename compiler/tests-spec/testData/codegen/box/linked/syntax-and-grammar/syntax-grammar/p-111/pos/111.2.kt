/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 111 -> sentence 111
 * NUMBER: 2
 * DESCRIPTION: literalConstant boolean literal - verifies that 'true' and 'false' are correctly parsed as boolean literals
 */
package syntax.grammar.p111.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val trueLiteral: Boolean = true
    val falseLiteral: Boolean = false
    
    // 显式比较字面量的值和预期值
    if (trueLiteral != true) return "fail: true literal not parsed correctly"
    if (falseLiteral != false) return "fail: false literal not parsed correctly"
    
    // 同时验证字面量的类型
    if (trueLiteral !is Boolean) return "fail: true literal is not Boolean"
    if (falseLiteral !is Boolean) return "fail: false literal is not Boolean"
    
    return "OK"
}