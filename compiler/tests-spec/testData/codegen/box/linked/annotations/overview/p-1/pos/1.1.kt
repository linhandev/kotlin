// WITH_STDLIB
// LANGUAGE: +InstantiationOfAnnotationClasses

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation class values can be created directly and used at runtime
 */

// TESTCASE NUMBER: 1

enum class Color17001 { RED, BLUE }

annotation class Nested17001(val tag: String)

annotation class Sample17001(
    val count: Int,
    val label: String,
    val color: Color17001,
    val nested: Nested17001,
    val tags: Array<String>
)

fun box(): String {
    val instance = Sample17001(
        count = 1,
        label = "ok",
        color = Color17001.RED,
        nested = Nested17001("nested"),
        tags = arrayOf("a", "b")
    )
    return if (instance.count == 1 &&
        instance.label == "ok" &&
        instance.color == Color17001.RED &&
        instance.nested.tag == "nested" &&
        instance.tags.contentEquals(arrayOf("a", "b"))
    ) {
        "OK"
    } else {
        "NOK"
    }
}
