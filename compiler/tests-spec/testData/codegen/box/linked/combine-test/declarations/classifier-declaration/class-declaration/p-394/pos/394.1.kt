// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 394 -> sentence 394
 * declarations, declaration-visibility -> paragraph 394 -> sentence 394
 * declarations, function-declaration -> paragraph 394 -> sentence 394
 * declarations, classifier-declaration, companion-object -> paragraph 394 -> sentence 394
 * NUMBER: 1
 * DESCRIPTION: 伴生对象内 private fun 仅能通过伴生/类内公开入口访问
 */

// TESTCASE NUMBER: 1
class Host { companion object { private fun secret(): Int = 1; fun api(): Int = secret() } }

// TESTCASE NUMBER: 1
fun test(): Int = Host.api()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
