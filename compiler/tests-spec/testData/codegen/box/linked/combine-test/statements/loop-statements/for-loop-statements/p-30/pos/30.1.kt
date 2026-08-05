// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: 可变集合在迭代中结构性修改可触发运行期异常（JVM ConcurrentModificationException）
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = try { val xs = arrayListOf(1, 2, 3); for (x in xs) { xs.add(99) }; false } catch (_: java.util.ConcurrentModificationException) { true }

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
