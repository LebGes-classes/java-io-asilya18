    import com.fasterxml.jackson.databind.ObjectMapper;
    import java.io.*;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.SerializationFeature;

    public class SerializationJson implements ObjectOutput {
        private final ObjectMapper objectMapper;
        // основной инструмент библиотеки в работе с json
        private final OutputStream outputStream;
        // поток вывода

        public SerializationJson(OutputStream outputStream) {
            this.objectMapper = new ObjectMapper();
            /* при вызове конструктора мы по-новому создаем
            внутренний инструмент класса
             */
             objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            this.outputStream = outputStream;
        }

        @Override
        public void writeObject(Object obj) throws IOException {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectMapper.writeValue(byteArrayOutputStream, obj);
                byteArrayOutputStream.writeTo(outputStream);
            } catch (JsonProcessingException e) {
                throw new NotSerializableException("JSON serialization failed: " + e.getMessage());
            } catch (IOException e) {
                throw new IOException("Failed to write to output stream: " + e.getMessage(), e);
            }
        } /* пометочка по исключениям : NotSerializableException наследуется от IOException,
        поэтому мы обязаны объявить throws IOException, иначе код не скомпилируется */

        // Добавляем наш новый метод
        public static void saveToFile(Object data, String filePath) throws IOException {
            File file = new File(filePath);
            new File(filePath).getParentFile().mkdirs();

            try (FileOutputStream fos = new FileOutputStream(filePath);
                 SerializationJson serializer = new SerializationJson(fos)) {
                serializer.writeObject(data);
            }
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            outputStream.write(b, off, len);
        }

        @Override
        public void flush() throws IOException {
            outputStream.flush();
        }

        @Override
        public void close() throws IOException {
            outputStream.close();
        }

        @Override
        // методы ниже имеются в интерфейсе ObjectOutput, но не реализованы за ненадобностью
        public void writeBoolean(boolean v) throws IOException {}

        @Override
        public void writeByte(int v) throws IOException {}

        @Override
        public void writeShort(int v) throws IOException {}

        @Override
        public void writeChar(int v) throws IOException {}

        @Override
        public void writeInt(int v) throws IOException {}

        @Override
        public void writeLong(long v) throws IOException {}

        @Override
        public void writeFloat(float v) throws IOException {}

        @Override
        public void writeDouble(double v) throws IOException {}

        @Override
        public void writeBytes(String s) throws IOException {}

        @Override
        public void writeChars(String s) throws IOException {}

        @Override
        public void writeUTF(String s) throws IOException {}
    }