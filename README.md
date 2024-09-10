# Gemini Test

Sample application that uses LangChain4J to interface with OpenAI's ChatGPT LLM.

Based on https://github.com/langchain4j/langchain4j-examples/blob/main/other-examples/src/main/java/HelloWorldExample.java

## To Compile

```bash
./gradlew assemble
```

## To Run

You’ll need an API key.  So just follow the instructions to
[obtain your OpenAI API key](https://platform.openai.com/api-keys).
Save it to the `.env` file as the `OPENAI_API_KEY` environment variable.  You 
can use `.env.template` as a guide to structure your `.env` file.

> I'm still working in getting the runtime to load the contents of `.env` 
> into the environment.

```bash
./gradlew run
```

## To Test

```bash
./gradlew check
```
